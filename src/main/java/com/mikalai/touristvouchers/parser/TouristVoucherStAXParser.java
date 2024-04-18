package com.mikalai.touristvouchers.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.Characters;
import java.io.FileInputStream;

public class TouristVoucherStAXParser {
  private static final Logger logger = LogManager.getLogger(TouristVoucherStAXParser.class);

  public void parseXMLFile(String filename) {
    try {
      XMLInputFactory factory = XMLInputFactory.newInstance();
      XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(filename));

      while (eventReader.hasNext()) {
        XMLEvent event = eventReader.nextEvent();

        if (event.isStartElement()) {
          StartElement startElement = event.asStartElement();
          String qName = startElement.getName().getLocalPart();

          if (qName.equalsIgnoreCase("standardVoucher") || qName.equalsIgnoreCase("extendedVoucher")) {
            System.out.println("Start Voucher: " + qName);
            String id = startElement.getAttributeByName(new QName("id")).getValue();
            System.out.println("ID: " + id);
          } else if (qName.equalsIgnoreCase("country")) {
            event = eventReader.nextEvent();
            if (event.isCharacters()) {
              Characters characters = event.asCharacters();
              System.out.println("Country: " + characters.getData());
            }
          }
        }
        if (event.isEndElement()) {
          String qName = event.asEndElement().getName().getLocalPart();
          if (qName.equalsIgnoreCase("standardVoucher") || qName.equalsIgnoreCase("extendedVoucher")) {
            System.out.println("End Voucher: " + qName);
          }
        }
      }
    } catch (Exception e) {
      logger.error("Error while parseXMLFile", e);
    }
  }

  public static void main(String[] args) {
    TouristVoucherStAXParser parser = new TouristVoucherStAXParser();
    parser.parseXMLFile("path_to_your_xml_file.xml");
  }
}
