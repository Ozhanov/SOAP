
package com.mirat.soap.client;

import com.example.soap.file.wsdl.GetFilesRequest;
import com.example.soap.file.wsdl.GetFilesResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class FilesHashClient extends WebServiceGatewaySupport {

	public GetFilesResponse getFilesHash(String name) {
		GetFilesRequest request = new GetFilesRequest();
		request.setName(name);
		return (GetFilesResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/files", request,
						new SoapActionCallback(
								"http://soap-files/soap-files-web-service/getFilesRequest"));
	}

}
