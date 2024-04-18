package com.mikalai.touristvouchers.parser;

import com.mikalai.touristvouchers.entity.*;
import com.mikalai.touristvouchers.type.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TouristVouchersDomParser {
  private static final Logger logger = LogManager.getLogger(TouristVouchersDomParser.class);
  private Set<AbstractTouristVoucher> vouchers;

  private DocumentBuilder docBuilder;

  public TouristVouchersDomParser() {
    vouchers = new HashSet<>();
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
      docBuilder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      logger.error("Error in configuring the document builder", e);
    }
  }

  public Set<AbstractTouristVoucher> getVouchers() {
    return vouchers;
  }

  public void buildSetVouchers(String filename) {
    Document doc;
    try {
      doc = docBuilder.parse(filename);
      Element root = doc.getDocumentElement();
      NodeList standardVouchersList = root.getElementsByTagName(VoucherXmlTag.STANDARD_VOUCHER.getValue());
      NodeList extendedVouchersList = root.getElementsByTagName(VoucherXmlTag.EXTENDED_VOUCHER.getValue());
      buildVouchers(standardVouchersList, VoucherCategory.STANDARD);
      buildVouchers(extendedVouchersList, VoucherCategory.EXTENDED);
    } catch (IOException | SAXException e) {
      logger.error("Error parsing the XML file", e);
    }
  }

  private void buildVouchers(NodeList vouchersList, VoucherCategory category) {
    for (int i = 0; i < vouchersList.getLength(); i++) {
      Element voucherElement = (Element) vouchersList.item(i);
      AbstractTouristVoucher voucher = (category == VoucherCategory.EXTENDED) ? buildExtendedVoucher(voucherElement) : buildStandardVoucher(voucherElement);
      vouchers.add(voucher);
    }
  }

  private AbstractTouristVoucher buildStandardVoucher(Element voucherElement) {
    StandardVoucher voucher = new StandardVoucher();
    fillCommonVoucherFields(voucher, voucherElement);

    String tourDescription = getElementTextContent(voucherElement, VoucherXmlTag.TOUR_DESCRIPTION.getValue());
    voucher.setTourDescription(tourDescription);

    return voucher;
  }

  private ExtendedVoucher buildExtendedVoucher(Element voucherElement) {
    ExtendedVoucher voucher = new ExtendedVoucher();
    fillCommonVoucherFields(voucher, voucherElement);
    Element hotelElement = (Element) voucherElement.getElementsByTagName(VoucherXmlTag.HOTEL.getValue()).item(0);
    ExtendedHotelCharacteristic hotel = new ExtendedHotelCharacteristic();
    if (hotelElement != null) {
      hotel.setStars(Integer.parseInt(getElementTextContent(hotelElement, VoucherXmlTag.STARS.getValue())));
      hotel.setMealIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, VoucherXmlTag.MEAL_INCLUDED.getValue())));
      String mealTypeStr = getElementTextContent(hotelElement, VoucherXmlTag.MEAL_TYPE.getValue());
      MealType mealType = MealType.fromString(mealTypeStr);
      hotel.setMealType(mealType);
      hotel.setRoomType(getElementTextContent(hotelElement, VoucherXmlTag.ROOM_TYPE.getValue()));
      hotel.setTvIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, VoucherXmlTag.TV_INCLUDED.getValue())));
      hotel.setAcIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, VoucherXmlTag.AC_INCLUDED.getValue())));
      hotel.setWifiIncluded(Boolean.parseBoolean(getElementTextContent(hotelElement, VoucherXmlTag.WIFI_INCLUDED.getValue())));
    }
    voucher.setHotel(hotel);
    return voucher;
  }

  private void fillCommonVoucherFields(AbstractTouristVoucher voucher, Element voucherElement) {
    voucher.setId(voucherElement.getAttribute(VoucherXmlTag.ID.getValue()));
    voucher.setStatus(voucherElement.getAttribute(VoucherXmlTag.STATUS.getValue()));
    voucher.setBookingNumber(voucherElement.getAttribute(VoucherXmlTag.BOOKING_NUMBER.getValue()));

    String typeStr = getElementTextContent(voucherElement, VoucherXmlTag.TYPE.getValue()).toUpperCase();
    VoucherType type = VoucherType.valueOf(typeStr);
    voucher.setType(type);

    voucher.setCountry(getElementTextContent(voucherElement, VoucherXmlTag.COUNTRY.getValue()));

    voucher.setDaysNights(getElementTextContent(voucherElement, VoucherXmlTag.DAYS_NIGHTS.getValue()));

    String issueDateStr = getElementTextContent(voucherElement, VoucherXmlTag.ISSUE_DATE.getValue());
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    LocalDateTime issueDate = LocalDateTime.parse(issueDateStr, formatter);
    voucher.setIssueDate(issueDate);

    String transportStr = getElementTextContent(voucherElement, VoucherXmlTag.TRANSPORT.getValue()).toUpperCase();
    TransportType transport = TransportType.valueOf(transportStr);
    voucher.setTransport(transport);

    String costStr = getElementTextContent(voucherElement, VoucherXmlTag.COST.getValue());
    BigDecimal cost = new BigDecimal(costStr);
    voucher.setCost(cost);
  }

  private static String getElementTextContent(Element element, String elementName) {
    NodeList nList = element.getElementsByTagName(elementName);
    Node node = nList.item(0);
    return node != null ? node.getTextContent() : "";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TouristVouchersDomParser that = (TouristVouchersDomParser) o;
    return Objects.equals(vouchers, that.vouchers) && Objects.equals(docBuilder, that.docBuilder);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vouchers, docBuilder);
  }

  @Override
  public String toString() {
    return "TouristVouchersDomBuilder{" +
            "vouchers=" + vouchers +
            ", docBuilder=" + docBuilder +
            '}';
  }
}

