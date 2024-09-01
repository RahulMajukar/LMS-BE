package com.hackifytech.edu.services;

import org.springframework.stereotype.Service;

@Service
public class CommonServices {

	public String otpTemplate(String otp) {
		return "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "  <meta charset=\"UTF-8\" />\r\n"
				+ "  <title></title>\r\n"
				+ "  <style>\r\n"
				+ "    body {\r\n"
				+ "      margin: 0;\r\n"
				+ "      padding: 0;\r\n"
				+ "      font-family: \"Helvetica Neue\", Helvetica, Arial, sans-serif;\r\n"
				+ "      color: #333;\r\n"
				+ "      background-color: #fff;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .container {\r\n"
				+ "      margin: 0 auto;\r\n"
				+ "      width: 100%;\r\n"
				+ "      max-width: 600px;\r\n"
				+ "      padding: 0 0px;\r\n"
				+ "      padding-bottom: 10px;\r\n"
				+ "      border-radius: 5px;\r\n"
				+ "      line-height: 1.8;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .header {\r\n"
				+ "      border-bottom: 1px solid #eee;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .header a {\r\n"
				+ "      font-size: 1.4em;\r\n"
				+ "      color: #000;\r\n"
				+ "      text-decoration: none;\r\n"
				+ "      font-weight: 600;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .content {\r\n"
				+ "      min-width: 700px;\r\n"
				+ "      overflow: auto;\r\n"
				+ "      line-height: 2;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .otp {\r\n"
				+ "      background: linear-gradient(to right, #00bc69 0, #00bc88 50%, #00bca8 100%);\r\n"
				+ "      margin: 0 auto;\r\n"
				+ "      width: max-content;\r\n"
				+ "      padding: 0 10px;\r\n"
				+ "      color: #fff;\r\n"
				+ "      border-radius: 4px;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .footer {\r\n"
				+ "      color: #aaa;\r\n"
				+ "      font-size: 0.8em;\r\n"
				+ "      line-height: 1;\r\n"
				+ "      font-weight: 300;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .email-info {\r\n"
				+ "      color: #666666;\r\n"
				+ "      font-weight: 400;\r\n"
				+ "      font-size: 13px;\r\n"
				+ "      line-height: 18px;\r\n"
				+ "      padding-bottom: 6px;\r\n"
				+ "    }\r\n"
				+ "\r\n"
				+ "    .email-info a {\r\n"
				+ "      text-decoration: none;\r\n"
				+ "      color: #00bc69;\r\n"
				+ "    }\r\n"
				+ "  </style>\r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "  <!--Subject: Login Verification Required for Your [App Name] Account-->\r\n"
				+ "  <div class=\"container\">\r\n"
				+ "    <div class=\"header\">\r\n"
				+ "      <a>Prove Your [App Name] Identity</a>\r\n"
				+ "    </div>\r\n"
				+ "    <br />\r\n"
				+ "    <strong>Dear [Recipient Name],</strong>\r\n"
				+ "    <p>\r\n"
				+ "      We have received a login request for your [App Name] account. For\r\n"
				+ "      security purposes, please verify your identity by providing the\r\n"
				+ "      following One-Time Password (OTP).\r\n"
				+ "      <br />\r\n"
				+ "      <b>Your One-Time Password (OTP) verification code is:</b>\r\n"
				+ "    </p>\r\n"
				+ "    <h2 class=\"otp\">"
				+ otp
				+ "</h2>\r\n"
				+ "    <p style=\"font-size: 0.9em\">\r\n"
				+ "      <strong>One-Time Password (OTP) is valid for 3 minutes.</strong>\r\n"
				+ "      <br />\r\n"
				+ "      <br />\r\n"
				+ "      If you did not initiate this login request, please disregard this\r\n"
				+ "      message. Please ensure the confidentiality of your OTP and do not share\r\n"
				+ "      it with anyone.<br />\r\n"
				+ "      <strong>Do not forward or give this code to anyone.</strong>\r\n"
				+ "      <br />\r\n"
				+ "      <br />\r\n"
				+ "      <strong>Thank you for using [App Name].</strong>\r\n"
				+ "      <br />\r\n"
				+ "      <br />\r\n"
				+ "      Best regards,\r\n"
				+ "      <br />\r\n"
				+ "      <strong>[Company Name]</strong>\r\n"
				+ "    </p>\r\n"
				+ "\r\n"
				+ "    <hr style=\"border: none; border-top: 0.5px solid #131111\" />\r\n"
				+ "    <div class=\"footer\">\r\n"
				+ "      <p>This email can't receive replies.</p>\r\n"
				+ "      <p>\r\n"
				+ "        For more information about [App Name] and your account, visit\r\n"
				+ "        <strong>[Name]</strong>\r\n"
				+ "      </p>\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "  <div style=\"text-align: center\">\r\n"
				+ "    <div class=\"email-info\">\r\n"
				+ "      <span>\r\n"
				+ "        This email was sent to\r\n"
				+ "        <a href=\"mailto:{Email Adress}\">{Email Adress}</a>\r\n"
				+ "      </span>\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"email-info\">\r\n"
				+ "      <a href=\"/\">[Company Name]</a> | [Address]\r\n"
				+ "      | [Address] - [Zip Code/Pin Code], [Country Name]\r\n"
				+ "    </div>\r\n"
				+ "    <div class=\"email-info\">\r\n"
				+ "      &copy; 2023 [Company Name]. All rights\r\n"
				+ "      reserved.\r\n"
				+ "    </div>\r\n"
				+ "  </div>\r\n"
				+ "</body>\r\n"
				+ "<!--    This template is made Redwan one from Ocoxe. -->\r\n"
				+ "<!-- https://www.ocoxe.com -->\r\n"
				+ "</html>";
	}
}
