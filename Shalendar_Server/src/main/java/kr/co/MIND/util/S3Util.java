package kr.co.MIND.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;



import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;

public class S3Util {

//	@Value("$s3Pro{s3.accessKey}")
	private String accessKey="AKIAWREI3DMMHJTOK36L";
	
//	@Value("$s3Pro{s3.secretKey}")
	private String secretKey="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";
	
	private AmazonS3 conn;

	public S3Util()  {
		
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,secretKey);
		ClientConfiguration clientConfig = new ClientConfiguration();
		clientConfig.setProtocol(Protocol.HTTP);
		this.conn = new AmazonS3Client(credentials, clientConfig);
		conn.setEndpoint("s3.ap-northeast-2.amazonaws.com"); // ��������Ʈ ���� [ �ƽþ� ����� ���� ]
	}

	// ��Ŷ ����Ʈ�� �������� �޼����̴�.
	public List<Bucket> getBucketList() {
		return conn.listBuckets();
	}

	// ��Ŷ�� �����ϴ� �޼����̴�.
	public Bucket createBucket(String bucketName) {
		return conn.createBucket(bucketName);
	}

	// ���� ���� (������ ���ϸ� �ڿ� "/"�� �ٿ����Ѵ�.)
	public void createFolder(String bucketName, String folderName) {
		conn.putObject(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]), new ObjectMetadata());
	}

	// ���� ���ε�
	public void fileUpload(String bucketName, String fileName, byte[] fileData) throws FileNotFoundException {

		String filePath = (fileName).replace(File.separatorChar, '/'); // ���� �����ڸ� `/`�� ����(\->/) �̰� ������ / ��� �Ѿ���鼭 \�� �ٲ�� �Ű���.
		ObjectMetadata metaData = new ObjectMetadata();

		metaData.setContentLength(fileData.length);   //��Ÿ������ ���� -->������ 128kB���� ���ε� ���������� ����ũ�⸸ŭ ���۸� �������״�.
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileData); //���� ����

		conn.putObject(bucketName, filePath, byteArrayInputStream, metaData);

	}

	// ���� ����
	public void fileDelete(String bucketName, String fileName) {
		String imgName = (fileName).replace(File.separatorChar, '/');
		conn.deleteObject(bucketName, imgName);
		System.out.println("��������");
	}

	// ���� URL
	public String getFileURL(String bucketName, String fileName) {
		System.out.println("�Ѿ���� ���ϸ� : "+fileName);
		String imgName = "https://shalendarmind.s3.ap-northeast-2.amazonaws.com/"+fileName.replace(File.separatorChar, '/');
		System.out.println(imgName);
		return imgName;
	}

}
