/*
 * Copyright 2016-2017 52°North Initiative for Geospatial Open Source
 * Software GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.svalbard.encode;

import javax.inject.Inject;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.n52.janmayen.Producer;
import org.n52.janmayen.http.MediaType;
import org.n52.janmayen.http.MediaTypes;
import org.n52.svalbard.EncodingContext;
import org.n52.svalbard.XmlEncoderKey;
import org.n52.svalbard.XmlHelper;
import org.n52.svalbard.encode.exception.EncodingException;
import org.n52.svalbard.encode.exception.NoEncoderForKeyException;

/**
 * @param <T>
 *
 * @since 4.0.0
 *
 * @param <S>
 */
public abstract class AbstractXmlEncoder<T, S>
        extends AbstractDelegatingEncoder<T, S>
        implements SchemaAwareEncoder<T, S> {

    private Producer<XmlOptions> xmlOptions;

    public XmlOptions getXmlOptions() {
        return xmlOptions.get();
    }

    @Inject
    public void setXmlOptions(Producer<XmlOptions> xmlOptions) {
        this.xmlOptions = xmlOptions;
    }

    @Override
    public T encode(S element) throws EncodingException {
        return encode(element, EncodingContext.empty());
    }

    @Override
    public MediaType getContentType() {
        return MediaTypes.TEXT_XML;
    }

    protected XmlObject substitute(XmlObject elementToSubstitute, XmlObject substitutionElement) {
        XmlObject substituteElement = XmlHelper.substituteElement(elementToSubstitute, substitutionElement);
        substituteElement.set(substitutionElement);
        return substituteElement;
    }

    public <T> Encoder<XmlObject, T> getEncoder(String namespace, T o) throws EncodingException {
        EncoderKey key = getEncoderKey(namespace, o);
        Encoder<XmlObject, T> encoder = getEncoder(key);
        if (encoder == null) {
            throw new NoEncoderForKeyException(key);
        }
        return encoder;
    }

    public <T> Encoder<XmlObject, T> getEncoder(String namespace, Class<? super T> o) throws EncodingException {
        EncoderKey key = getEncoderKey(namespace, o);
        Encoder<XmlObject, T> encoder = getEncoder(key);
        if (encoder == null) {
            throw new NoEncoderForKeyException(key);
        }
        return encoder;
    }

    public <T> XmlObject encodeObjectToXml(String namespace, T object, EncodingContext helperValues)
            throws EncodingException {
        return getEncoder(namespace, object).encode(object, helperValues == null ? EncodingContext.empty() : helperValues);
    }

    public XmlObject encodeObjectToXml(String namespace, Object object) throws EncodingException {
        return encodeObjectToXml(namespace, object, null);
    }

    public String encodeObjectToXmlText(String namespace, Object object, EncodingContext helperValues) throws EncodingException {
        return encodeObjectToXml(namespace, object, helperValues).xmlText(getXmlOptions());
    }

    public String encodeObjectToXmlText(String namespace, Object object) throws EncodingException {
        return encodeObjectToXmlText(namespace, object, null);
    }

    public EncoderKey getEncoderKey(String namespace, Object o) {
        return new XmlEncoderKey(namespace, o.getClass());
    }

    public EncoderKey getEncoderKey(String namespace, Class<?> o) {
        return new XmlEncoderKey(namespace, o);
    }

}
