import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import chttl.cloud.hws.HiCloudWebServiceClient;
import chttl.cloud.hws.auth.HwsCredential;
import chttl.cloud.hws.auth.HwsPropertyCredential;
import chttl.cloud.hws.exception.HiCloudClientException;
import chttl.cloud.hws.exception.HiCloudServerException;
import chttl.cloud.hws.services.caas.VirtualMachineService;
import chttl.cloud.hws.services.caas.model.DescribeInstancesRequest;
import chttl.cloud.hws.services.caas.model.DescribeInstancesResponse;
import chttl.cloud.hws.services.caas.model.StartInstancesRequest;
import chttl.cloud.hws.services.caas.model.StartInstancesResponse;
import chttl.cloud.hws.services.caas.model.VirtualMachineEntry;

public class listVM
{
    public static void main(String[] args)
	throws HiCloudClientException, HiCloudServerException
    {
	// 新增憑證(使用Property檔案)
	HwsCredential credential = null;
	try {
	    credential = new HwsPropertyCredential();
	} catch (FileNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} catch (IllegalArgumentException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	// 建立Client End
	HiCloudWebServiceClient client = new HiCloudWebServiceClient("hws.hicloud.hinet.net", credential);

	// 若使用Proxy Server，可透過以下方式設定
	//client.getConfiguration().setProxyServerIp("proxy.cht.com.tw");
	//client.getConfiguration().setProxyServerPort(8080);
	
	// 略過 hws.hicloud.hint.net 的https憑證檢查
	client.getConfiguration().setHttpsVerify(false);

	// 建立VM Service Proxy，負責代理處理參數傳遞與資料驗證
	VirtualMachineService serviceProxy = client.newServiceProxy(VirtualMachineService.class);

	DescribeInstancesRequest request = new DescribeInstancesRequest();
	// 呼叫CaaS API建立VM並回傳RunInstancesResponse
	DescribeInstancesResponse describeInstancesResult = serviceProxy.describeInstances(request);
	// 顯示DescribeInstancesResponse結果
	System.out.println(describeInstancesResult);
    }
}
