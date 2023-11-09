package com.example.talk2friends;

import android.app.Activity;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class InviteFriendActivity extends Activity {

    private EditText editTextEmail;
    private Button buttonSendInvite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);

        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSendInvite = findViewById(R.id.buttonSendInvite);

        buttonSendInvite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextEmail.getText().toString().trim();
                if (!email.isEmpty() && email.endsWith("@usc.edu")) {
                    sendInvitationEmail(email);
                } else {
                    Toast.makeText(InviteFriendActivity.this, "Please enter a valid USC email address.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendInvitationEmail(final String email) {
        final String verificationCode = generateVerificationCode();

        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    Properties props = new Properties();
                    props.put("mail.smtp.host", "smtp.gmail.com"); // for example, if you're using Google's SMTP server
                    props.put("mail.smtp.socketFactory.port", "465");
                    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "465");

                    Session session = Session.getInstance(props, new Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication("team60usc@gmail.com", "Clay2000@");
                        }
                    });

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("team60usc@gmail.com")); // sender email
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                    message.setSubject("Invitation to Join Talk2Friends");
                    message.setText("Hello! You have been invited to join Talk2Friends. "
                            + "Please use the following link to sign up. Your verification code is: " + verificationCode
                            + "\n\nLink: PLEASE BLESS US WITH A GOOD GRADE"); //LINK CODE

                    Transport.send(message);

                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            protected void onPostExecute(Boolean success) {
                if (success) {
                    Toast.makeText(InviteFriendActivity.this, "Invitation sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(InviteFriendActivity.this, "Failed to send the invitation.", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }
}