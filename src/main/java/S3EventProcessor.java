import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import java.io.InputStream;

public class S3EventProcessor implements RequestHandler<S3Event, String> {

    public String handleRequest(S3Event s3event, Context context) {
        AmazonS3 s3Client = new AmazonS3Client();
        S3EventNotificationRecord record = s3event.getRecords().get(0);
        String s3Bucket = record.getS3().getBucket().getName();
        String s3Key = record.getS3().getObject().getKey();
        String newKey = s3Key + "new";
        S3Object s3Object = s3Client.getObject(new GetObjectRequest(s3Bucket, s3Key));
        InputStream objectData = s3Object.getObjectContent();
        s3Client.putObject(s3Bucket, newKey, objectData, new ObjectMetadata());
        return "Ok";
    }
}