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
package org.n52.svalbard.sos.v1;

import org.apache.xmlbeans.XmlObject;
import org.n52.shetland.ogc.ows.exception.OwsExceptionReport;
import org.n52.shetland.ogc.sos.SosConstants;
import org.n52.shetland.ogc.sos.response.GetObservationResponse;
import org.n52.svalbard.encode.Encoder;
import org.n52.svalbard.encode.exception.EncodingException;

/**
 * TODO JavaDoc
 *
 * @author Christian Autermann <c.autermann@52north.org>
 *
 * @since 4.0.0
 */
public class GetObservationResponseEncoder extends AbstractSosResponseEncoder<GetObservationResponse> {
    public GetObservationResponseEncoder() {
        super(SosConstants.Operations.GetObservation.name(), GetObservationResponse.class);
    }

    @Override
    protected XmlObject create(GetObservationResponse response) throws EncodingException {
        String responseFormat = response.getResponseFormat();
        Encoder<XmlObject, GetObservationResponse> encoder = getEncoder(responseFormat, response);
        if (response.hasStreamingData()) {
            try {
                response.mergeStreamingData();
            } catch (OwsExceptionReport owse) {
                throw new EncodingException(owse);
            }
        }
        return encoder.encode(response);
    }
}
