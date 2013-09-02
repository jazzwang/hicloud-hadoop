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
import chttl.cloud.hws.services.caas.model.RunInstancesRequest;
import chttl.cloud.hws.services.caas.model.RunInstancesResponse;
import chttl.cloud.hws.services.caas.model.VirtualMachineEntry;

public class newVM
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

	// 建立VM Service Proxy，負責代理處理參數傳遞與資料驗證
	VirtualMachineService serviceProxy = client.newServiceProxy(VirtualMachineService.class);

	// 步驟1:新增VM
	// 新增Run Instance Request
	// ImageId and InstanceType Reference: 
	// http://hws.hicloud.hinet.net/hws-doc/rest/vm/template_instancetype_mapping.html
	RunInstancesRequest request = new RunInstancesRequest();
	request.setImageId("hi-dgf5332e");
	request.setCount(1);
	request.setInstanceName("hdp");
	request.setMonitoringEnabled(false);
	request.setInstanceType("HC1.S.LINUX");
	// 呼叫CaaS API建立VM並回傳RunInstancesResponse
	RunInstancesResponse runInstancesResult = serviceProxy.runInstances(request);
	// 顯示RunInstancesResponse結果
	System.out.println(runInstancesResult);

	// 步驟2:查詢虛擬機資訊
	// 新增Describe Instance Request
	DescribeInstancesRequest describeRequest = new DescribeInstancesRequest();
	// 根據建立RunInstance回傳的uuid查詢，虛擬機訂單狀態
	describeRequest.setOrderUuid(runInstancesResult.getOrderUuidList());

	boolean isProvisionFinished = false;
	// 持續查詢虛擬機資訊，直到虛擬機供裝完畢
	while (!isProvisionFinished)
	{
	    // 呼叫CaaS API查詢VM並回傳DescribeInstancesResponse
	    DescribeInstancesResponse describeInstancesResult = serviceProxy.describeInstances(describeRequest);
	    // 顯示VM訂單狀態
	    System.out.println(describeInstancesResult);
	    List<VirtualMachineEntry> instances = describeInstancesResult.getInstanceList();
	    for (VirtualMachineEntry instance : instances)
	    {
		if (!instance.getProvisionStatus().equals("provisioning"))
		{
		    isProvisionFinished = true;
		}
	    }

	    try
	    {
		Thread.sleep(5000);
	    }
	    catch (InterruptedException e)
	    {
		e.printStackTrace();
	    }
	}
    }
}
