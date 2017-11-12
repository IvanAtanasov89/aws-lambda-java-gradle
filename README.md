# aws-lambda-java-gradle

Example project that uses gradle to build a function to be deployed as an AWS lambda.

All this does as an example is responds to an S3 put event by creating a copy of the file
in the same bucket but with a suffix of 'new'

Deploy it to AWS using:

```bash
aws cloudformation deploy --template-file template.yaml --stack-name lambda-test --capabilities CAPABILITY_IAM
```
