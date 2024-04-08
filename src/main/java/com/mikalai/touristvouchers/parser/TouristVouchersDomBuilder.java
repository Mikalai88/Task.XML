package com.mikalai.touristvouchers.parser;

import com.mikalai.touristvouchers.entity.ExtendedHotelCharacteristic;
import com.mikalai.touristvouchers.entity.StandardVoucher;
import com.mikalai.touristvouchers.entity.TouristVoucher;
import com.mikalai.touristvouchers.entity.ExtendedVoucher;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.mikalai.touristvouchers.enums.MealType;
import com.mikalai.touristvouchers.enums.TransportType;
import com.mikalai.touristvouchers.enums.VoucherType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TouristVouchersDomBuilder {
  private Set<TouristVoucher> vouchers;

  private DocumentBuilder docBuilder;

  public TouristVouchersDomBuilder() {
    vouchers = new HashSet<>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      docBuilder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace(); // log
    }
  }

  public Set<TouristVoucher> getVouchers() {
    return vouchers;
  }

  public void buildSetVouchers(String filename) {
    Document doc;
    try {
      doc = docBuilder.parse(filename);
      Element root = doc.getDocumentElement();
      NodeList standardVouchersList = root.getElementsByTagName("standardVoucher");
      NodeList extendedVouchersList = root.getElementsByTagName("extendedVoucher");
      buildVouchers(standardVouchersList, false);
      buildVouchers(extendedVouchersList, true);
    } catch (IOException | SAXException e) {
      e.printStackTrace(); // log
    }
  }

  private void buildVouchers(NodeList vouchersList, boolean isExtended) {
    for (int i = 0; i < vouchersList.getLength(); i++) {
      Element voucherElement = (Element) vouchersList.item(i);
      TouristVoucher voucher = isExtended ? buildExtendedVoucher(voucherElement) : buildStandardVoucher(voucherElement);
      vouchers.add(voucher);
    }
  }

  private TouristVoucher buildStandardVoucher(Element voucherElement) {
    TouristVoucher voucher = new StandardVoucher();
    fillCommonVoucherFields(voucher, voucherElement);
    return voucher;
  }

  private ExtendedVoucher buildExtendedVoucher(Element voucherElement) {
    ExtendedVoucher voucher = new ExtendedVoucher();
    fillCommonVoucherFields(voucher, voucherElement);
    Element hotelElement = (Element) voucherElement.getElementsByTagName("hotel").item(0);
    ExtendedHotelCharacteristic hotel = new ExtendedHotelCharacteristic();
    if (hotelElement != null) {
      hotel.setStars(Integer.parseInt(getElementTextContent(hotelElement, "stars")));
      hotel.setMealIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, "mealIncluded")));
      String mealTypeStr = getElementTextContent(hotelElement, "mealType");
      MealType mealType = MealType.fromString(mealTypeStr);
      hotel.setMealType(mealType);
      hotel.setRoomType(getElementTextContent(hotelElement, "roomType"));
      hotel.setTvIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, "tvIncluded")));
      hotel.setAcIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, "acIncluded")));
      hotel.setWifiIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, "wifiIncluded")));
    }
    voucher.setHotel(hotel);
    return voucher;
  }


  private void fillCommonVoucherFields(TouristVoucher voucher, Element voucherElement) {
    voucher.setId(voucherElement.getAttribute("id"));

    String typeStr = getElementTextContent(voucherElement, "type").toUpperCase();
    VoucherType type = VoucherType.valueOf(typeStr);
    voucher.setType(type);

    voucher.setCountry(getElementTextContent(voucherElement, "country"));

    voucher.setDaysNights(getElementTextContent(voucherElement, "daysNights"));

    String issueDateStr = getElementTextContent(voucherElement, "issueDate");
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    LocalDateTime issueDate = LocalDateTime.parse(issueDateStr, formatter);
    voucher.setIssueDate(issueDate);

    String transportStr = getElementTextContent(voucherElement, "transport").toUpperCase();
    TransportType transport = TransportType.valueOf(transportStr);
    voucher.setTransport(transport);

    String costStr = getElementTextContent(voucherElement, "cost");
    BigDecimal cost = new BigDecimal(costStr);
    voucher.setCost(cost);
  }


  private static String getElementTextContent(Element element, String elementName) {
    NodeList nList = element.getElementsByTagName(elementName);
    Node node = nList.item(0);
    return node != null ? node.getTextContent() : "";
  }
}

