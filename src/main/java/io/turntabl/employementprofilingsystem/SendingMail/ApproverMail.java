package io.turntabl.employementprofilingsystem.SendingMail;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class ApproverMail {
    public static void requestMessage(String to, String from, String subject, String requester_start_date, String requester_end_date) throws IOException, GeneralSecurityException {
        String bodyText = "<!doctype html>\n" +
                "  <html>\n" +
                "    <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>holiday request</title>\n" +
                "    <style>\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "      font-size: 28px !important;\n" +
                "      margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "        table[class=body] ul,\n" +
                "        table[class=body] ol,\n" +
                "        table[class=body] td,\n" +
                "        table[class=body] span,\n" +
                "        table[class=body] a {\n" +
                "      font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "        table[class=body] .article {\n" +
                "      padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "      padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "      padding: 0 !important;\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "      border-left-width: 0 !important;\n" +
                "      border-radius: 0 !important;\n" +
                "      border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "      height: auto !important;\n" +
                "      max-width: 100% !important;\n" +
                "      width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "      width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "        .ExternalClass p,\n" +
                "        .ExternalClass span,\n" +
                "        .ExternalClass font,\n" +
                "        .ExternalClass td,\n" +
                "        .ExternalClass div {\n" +
                "      line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "      color: inherit !important;\n" +
                "      font-family: inherit !important;\n" +
                "      font-size: inherit !important;\n" +
                "      font-weight: inherit !important;\n" +
                "      line-height: inherit !important;\n" +
                "      text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "      font-size: inherit;\n" +
                "      font-family: inherit;\n" +
                "      font-weight: inherit;\n" +
                "      line-height: inherit;\n" +
                "      }\n" +
                "      .btn-primary table td:hover {\n" +
                "      background-color: #003b00 !important;\n" +
                "      }\n" +
                "      .btn-primary a:hover {\n" +
                "      background-color: #003b00 !important;\n" +
                "      border-color: #003b00 !important;\n" +
                "      }\n" +
                "    }\n" +
                "    </style>\n" +
                "    </head>\n" +
                "    <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "        <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "        <span class=\"preheader\" style=\"color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;\"></span>\n" +
                "        <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "          <tr>\n" +
                "          <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "            <tr>\n" +
                "              <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Hi there,</p>\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Holiday request by " + from + "</p>\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Start date: " + requester_start_date + "</p>\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Report date: " + requester_end_date + "</p>\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                  <td align=\"center\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; background-color: #3498db; border-radius: 5px; text-align: center;\"><a href =\""+System.getenv("REDIRECT_TO_APPROVER_DASHBOARD")+"\" target=\"_blank\" style=\"display: inline-block; color: #ffffff; background-color: green; border: solid 1px green; border-radius: 5px; box-sizing: border-box; cursor: pointer; text-decoration: none; font-size: 14px; font-weight: bold; margin: 0; padding: 12px 25px; text-transform: capitalize; border-color: green;\"> Take Action </a> </td>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "              </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "          <tr>\n" +
                "            <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +
                "            <span class=\"apple-link\" style=\"color: #999999; font-size: 12px; text-align: center;\">Turntabl, Achimota, Mile 7, Sonnidom house</span>\n" +
                "            <br><a href=\"http://turntabl.io\" style=\"text-decoration: underline; color: #999999; font-size: 12px; text-align: center;\">Turntabl</a>.\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </table>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "    </body>\n" +
                "  </html>";

        GmailService.sendMail(from, to, subject, bodyText.toString());
    }


        public static void approveMessage(String to, String from, String subject, String requester_start_date, String requester_end_date) throws IOException, GeneralSecurityException {
        String bodyText = "<!doctype html>\n" +
                "  <html>\n" +
                "    <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>holiday request</title>\n" +
                "    <style>\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "      font-size: 28px !important;\n" +
                "      margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "        table[class=body] ul,\n" +
                "        table[class=body] ol,\n" +
                "        table[class=body] td,\n" +
                "        table[class=body] span,\n" +
                "        table[class=body] a {\n" +
                "      font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "        table[class=body] .article {\n" +
                "      padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "      padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "      padding: 0 !important;\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "      border-left-width: 0 !important;\n" +
                "      border-radius: 0 !important;\n" +
                "      border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "      height: auto !important;\n" +
                "      max-width: 100% !important;\n" +
                "      width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "      width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "        .ExternalClass p,\n" +
                "        .ExternalClass span,\n" +
                "        .ExternalClass font,\n" +
                "        .ExternalClass td,\n" +
                "        .ExternalClass div {\n" +
                "      line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "      color: inherit !important;\n" +
                "      font-family: inherit !important;\n" +
                "      font-size: inherit !important;\n" +
                "      font-weight: inherit !important;\n" +
                "      line-height: inherit !important;\n" +
                "      text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "      font-size: inherit;\n" +
                "      font-family: inherit;\n" +
                "      font-weight: inherit;\n" +
                "      line-height: inherit;\n" +
                "      }\n" +
                "      .btn-primary table td:hover {\n" +
                "      background-color: #003b00 !important;\n" +
                "      }\n" +
                "      .btn-primary a:hover {\n" +
                "      background-color: #003b00 !important;\n" +
                "      border-color: #003b00 !important;\n" +
                "      }\n" +
                "    }\n" +
                "    </style>\n" +
                "    </head>\n" +
                "    <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "        <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "        <span class=\"preheader\" style=\"color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;\"></span>\n" +
                "        <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "          <tr>\n" +
                "          <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "            <tr>\n" +
                "              <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Hi there, your request was approved. Enjoy your holidays!!!</p>\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Start date: " + requester_start_date + "</p>\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Report date: " + requester_end_date + "</p>\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                  <td align=\"center\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "              </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "          <tr>\n" +
                "            <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +
                "            <span class=\"apple-link\" style=\"color: #999999; font-size: 12px; text-align: center;\">Turntabl, Achimota, Mile 7, Sonnidom house</span>\n" +
                "            <br><a href=\"http://turntabl.io\" style=\"text-decoration: underline; color: #999999; font-size: 12px; text-align: center;\">Turntabl</a>.\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </table>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "    </body>\n" +
                "  </html>";

        GmailService.sendMail(from, to, subject, bodyText.toString());
    }

        public static void declinedMessage(String to, String from, String subject, String requester_start_date, String requester_end_date) throws IOException, GeneralSecurityException {
        String bodyText = "<!doctype html>\n" +
                "  <html>\n" +
                "    <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\">\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "    <title>holiday request</title>\n" +
                "    <style>\n" +
                "    @media only screen and (max-width: 620px) {\n" +
                "      table[class=body] h1 {\n" +
                "      font-size: 28px !important;\n" +
                "      margin-bottom: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] p,\n" +
                "        table[class=body] ul,\n" +
                "        table[class=body] ol,\n" +
                "        table[class=body] td,\n" +
                "        table[class=body] span,\n" +
                "        table[class=body] a {\n" +
                "      font-size: 16px !important;\n" +
                "      }\n" +
                "      table[class=body] .wrapper,\n" +
                "        table[class=body] .article {\n" +
                "      padding: 10px !important;\n" +
                "      }\n" +
                "      table[class=body] .content {\n" +
                "      padding: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .container {\n" +
                "      padding: 0 !important;\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .main {\n" +
                "      border-left-width: 0 !important;\n" +
                "      border-radius: 0 !important;\n" +
                "      border-right-width: 0 !important;\n" +
                "      }\n" +
                "      table[class=body] .btn table {\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .btn a {\n" +
                "      width: 100% !important;\n" +
                "      }\n" +
                "      table[class=body] .img-responsive {\n" +
                "      height: auto !important;\n" +
                "      max-width: 100% !important;\n" +
                "      width: auto !important;\n" +
                "      }\n" +
                "    }\n" +
                "    @media all {\n" +
                "      .ExternalClass {\n" +
                "      width: 100%;\n" +
                "      }\n" +
                "      .ExternalClass,\n" +
                "        .ExternalClass p,\n" +
                "        .ExternalClass span,\n" +
                "        .ExternalClass font,\n" +
                "        .ExternalClass td,\n" +
                "        .ExternalClass div {\n" +
                "      line-height: 100%;\n" +
                "      }\n" +
                "      .apple-link a {\n" +
                "      color: inherit !important;\n" +
                "      font-family: inherit !important;\n" +
                "      font-size: inherit !important;\n" +
                "      font-weight: inherit !important;\n" +
                "      line-height: inherit !important;\n" +
                "      text-decoration: none !important;\n" +
                "      }\n" +
                "      #MessageViewBody a {\n" +
                "      color: inherit;\n" +
                "      text-decoration: none;\n" +
                "      font-size: inherit;\n" +
                "      font-family: inherit;\n" +
                "      font-weight: inherit;\n" +
                "      line-height: inherit;\n" +
                "      }\n" +
                "      .btn-primary table td:hover {\n" +
                "      background-color: #003b00 !important;\n" +
                "      }\n" +
                "      .btn-primary a:hover {\n" +
                "      background-color: #003b00 !important;\n" +
                "      border-color: #003b00 !important;\n" +
                "      }\n" +
                "    }\n" +
                "    </style>\n" +
                "    </head>\n" +
                "    <body class=\"\" style=\"background-color: #f6f6f6; font-family: sans-serif; -webkit-font-smoothing: antialiased; font-size: 14px; line-height: 1.4; margin: 0; padding: 0; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%;\">\n" +
                "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background-color: #f6f6f6;\">\n" +
                "      <tr>\n" +
                "      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      <td class=\"container\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; display: block; Margin: 0 auto; max-width: 580px; padding: 10px; width: 580px;\">\n" +
                "        <div class=\"content\" style=\"box-sizing: border-box; display: block; Margin: 0 auto; max-width: 580px; padding: 10px;\">\n" +
                "        <span class=\"preheader\" style=\"color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0;\"></span>\n" +
                "        <table class=\"main\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; background: #ffffff; border-radius: 3px;\">\n" +
                "          <tr>\n" +
                "          <td class=\"wrapper\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; box-sizing: border-box; padding: 20px;\">\n" +
                "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "            <tr>\n" +
                "              <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Hi there, Sorry your holiday request was declined. </p>\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Start date: " + requester_start_date + "</p>\n" +
                "              <p style=\"font-family: sans-serif; font-size: 14px; font-weight: normal; margin: 0; Margin-bottom: 15px; color: black;\">Report date: " + requester_end_date + "</p>\n" +
                "              <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; box-sizing: border-box;\">\n" +
                "                <tbody>\n" +
                "                <tr>\n" +
                "                  <td align=\"center\" style=\"font-family: sans-serif; font-size: 14px; vertical-align: top; padding-bottom: 15px;\">\n" +
                "                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: auto;\">\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                    </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                  </td>\n" +
                "                </tr>\n" +
                "                </tbody>\n" +
                "              </table>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "            </table>\n" +
                "          </td>\n" +
                "          </tr>\n" +
                "        </table>\n" +
                "        <div class=\"footer\" style=\"clear: both; Margin-top: 10px; text-align: center; width: 100%;\">\n" +
                "          <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%;\">\n" +
                "          <tr>\n" +
                "            <td class=\"content-block\" style=\"font-family: sans-serif; vertical-align: top; padding-bottom: 10px; padding-top: 10px; font-size: 12px; color: #999999; text-align: center;\">\n" +
                "            <span class=\"apple-link\" style=\"color: #999999; font-size: 12px; text-align: center;\">Turntabl, Achimota, Mile 7, Sonnidom house</span>\n" +
                "            <br><a href=\"http://turntabl.io\" style=\"text-decoration: underline; color: #999999; font-size: 12px; text-align: center;\">Turntabl</a>.\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "          </table>\n" +
                "        </div>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "      <td style=\"font-family: sans-serif; font-size: 14px; vertical-align: top;\">&nbsp;</td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "    </body>\n" +
                "  </html>";

        GmailService.sendMail(from, to, subject, bodyText.toString());
    }

}


