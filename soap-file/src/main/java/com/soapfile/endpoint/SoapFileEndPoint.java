package com.soapfile.endpoint;

import com.soapfile.service.SoapFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import soap_files.soap_files_web_service.GetFilesRequest;
import soap_files.soap_files_web_service.GetFilesResponse;


@Endpoint
public class SoapFileEndPoint {
    private static final String NAMESPACE = "http://soap-files/soap-files-web-service";
    private SoapFilesService service;

    @Autowired
    public SoapFileEndPoint(SoapFilesService _service) {
        this.service = _service;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getFilesRequest")
    @ResponsePayload
    public GetFilesResponse getFiles(@RequestPayload GetFilesRequest request) {
        GetFilesResponse response = new GetFilesResponse();
        var fileHash = service.getFiles(request);
        response.setFileHash(fileHash);
        return response;
    }
}
