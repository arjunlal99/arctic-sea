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
package org.n52.svalbard;

import javax.xml.stream.XMLStreamException;

import org.n52.shetland.ogc.om.OmObservation;
import org.n52.svalbard.encode.exception.EncodingException;

/**
 * Implementation of {@link AbstractOmV20XmlStreamWriter} to write WaterML 2.0
 * Domain Range encoded {@link OmObservation}s to stream
 *
 * @author <a href="mailto:c.hollmann@52north.org">Carsten Hollmann</a>
 * @since 4.1.0
 *
 */
public class WmlTDREncoderv20XmlStreamWriter extends AbstractOmV20XmlStreamWriter {

    /**
     * constructor
     */
    public WmlTDREncoderv20XmlStreamWriter() {
        super();
    }

    /**
     * constructor
     *
     * @param observation
     *                    {@link OmObservation} to write to stream
     */
    public WmlTDREncoderv20XmlStreamWriter(OmObservation observation) {
        super(observation);
    }

    @Override
    protected void writeResult(OmObservation observation, EncodingValues encodingValues) throws EncodingException,
                                                                                                XMLStreamException {
        // TODO Auto-generated method stub
        super.writeResult(observation, encodingValues);

    }

}
