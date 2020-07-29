package com.cogent.insurance.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.cogent.insurance.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AmazonSES {
  // This address must be verified with Amazon SES.
  final String FROM = "koblosh.alex@gmail.com";

  // The subject line for the email.
  final String SUBJECT = "One last step to complete your registration with Spring Insurance";

  // The HTML body for the email.
  final String HTMLBODY =
      "<h1>Please verify your email address</h1>"
          + "<p>Thank you for registering with our mobile app. To complete registration process and be able to log in,"
          + " click on the following link: "
          + "<a href='http://localhost:8081/verification_service_war/email-verification.html?token=$tokenValue'>"
          + "Final step to complete your registration"
          + "</a><br/><br/>"
          + "Thank you! And we are waiting for you inside!";

  // The email body for recipients with non-HTML email clients.
  final String TEXTBODY =
      "Please verify your email address. "
          + "Thank you for registering with our mobile app. To complete registration process and be able to log in,"
          + " open then the following URL in your browser window: "
          + " http://localhost:8081/verification_service_war/email-verification.html?token=$tokenValue"
          + " Thank you! And we are waiting for you inside!";



  public void verifyEmail(User user) {

    // You can also set your keys this way. And it will work!
    System.setProperty("aws.accessKeyId", "AKIAUZDYMP6TJFDVAUXW");
    System.setProperty("aws.secretKey", "FsC4qlNMaYaDvytNzeyif8fe4KfbPW2QaaZzA/qQ");

    AmazonSimpleEmailService client =
        AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_2).build();

    String htmlBodyWithToken = HTMLBODY.replace("$tokenValue", user.getEmailVerificationToken());
    String textBodyWithToken = TEXTBODY.replace("$tokenValue", user.getEmailVerificationToken());

    SendEmailRequest request =
        new SendEmailRequest()
            .withDestination(new Destination().withToAddresses(user.getEmail()))
            .withMessage(
                new Message()
                    .withBody(
                        new Body()
                            .withHtml(
                                new Content().withCharset("UTF-8").withData(htmlBodyWithToken))
                            .withText(
                                new Content().withCharset("UTF-8").withData(textBodyWithToken)))
                    .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
            .withSource(FROM);

    client.sendEmail(request);

    System.out.println("Email sent!");
  }
}
