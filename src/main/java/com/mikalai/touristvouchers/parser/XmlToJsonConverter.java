package com.mikalai.touristvouchers.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.IOException;

public class XmlToJsonConverter {
  public static void main(String[] args) {
    try {
      XmlMapper xmlMapper = new XmlMapper();
      ObjectMapper jsonMapper = new ObjectMapper();

      File xmlFile = new File("dataxml/TouristVouchers.xml");
      Object xmlObject = xmlMapper.readValue(xmlFile, Object.class);

      String json = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(xmlObject);

      File jsonFile = new File("datajson/output.json");
      jsonMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, xmlObject);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
