package com.mikalai.touristvouchers.parser;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class TouristVoucherSAXParser {

  public static void main(String[] args) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      SAXParser saxParser = factory.newSAXParser();

      DefaultHandler handler = new DefaultHandler() {

        boolean inVoucher = false;

        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
          if (qName.equalsIgnoreCase("standardVoucher") || qName.equalsIgnoreCase("extendedVoucher")) {
            inVoucher = true;
            System.out.println("Voucher ID: " + attributes.getValue("id"));
            System.out.println("Voucher Status: " + attributes.getValue("status"));
            System.out.println("Booking Number: " + attributes.getValue("bookingNumber"));
          }

          if (inVoucher && qName.equalsIgnoreCase("country")) {
            System.out.print("Country: ");
          }
        }

        public void characters(char ch[], int start, int length) throws SAXException {
          if (inVoucher) {
            System.out.println(new String(ch, start, length));
          }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException {
          if (qName.equalsIgnoreCase("standardVoucher") || qName.equalsIgnoreCase("extendedVoucher")) {
            inVoucher = false;
            System.out.println("End of Voucher");
          }
        }
      };

      saxParser.parse("dataxml/TouristVouchers.xml", handler);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

