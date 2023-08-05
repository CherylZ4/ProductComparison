package com.example.ProductComparison.api.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Objects;






/**
 * ProductRecord
 */


public class ProductRecord {

  private Double percsil;

  private String product1;

  private String product2;

  private String ingrCommon;

  private String p1ingr;

  private String p2ingr;


  public ProductRecord(Double percsil, String product1, String product2, String p1ingr, String p2ingr,
                       String ingrCommon) {
    this.percsil = percsil;
    this.product1 = product1;
    this.product2 = product2;
    this.p1ingr = p1ingr;
    this.p2ingr = p2ingr;
    this.ingrCommon = ingrCommon;
  }

  public ProductRecord percsil(Double percsil) {
    this.percsil = percsil;
    return this;
  }

  /**
   * Get percsil
   * @return percsil
  */

  @JsonProperty("percsil")
  public Double getPercsil() {
    return percsil;
  }

  public void setPercsil(Double percsil) {
    this.percsil = percsil;
  }

  public ProductRecord product1(String product1) {
    this.product1 = product1;
    return this;
  }

  /**
   * Get product1
   * @return product1
  */

  @JsonProperty("product1")
  public String getProduct1() {
    return product1;
  }

  public void setProduct1(String product1) {
    this.product1 = product1;
  }

  public ProductRecord product2(String product2) {
    this.product2 = product2;
    return this;
  }

  /**
   * Get product2
   * @return product2
  */

  @JsonProperty("product2")
  public String getProduct2() {
    return product2;
  }

  public void setProduct2(String product2) {
    this.product2 = product2;
  }

  public ProductRecord ingrCommon(String ingrCommon) {
    this.ingrCommon = ingrCommon;
    return this;
  }

  /**
   * Get ingrCommon
   * @return ingrCommon
  */
  

  @JsonProperty("ingr_common")
  public String getIngrCommon() {
    return ingrCommon;
  }

  public void setIngrCommon(String ingrCommon) {
    this.ingrCommon = ingrCommon;
  }

  public ProductRecord p1ingr(String p1ingr) {
    this.p1ingr = p1ingr;
    return this;
  }

  /**
   * Get p1ingr
   * @return p1ingr
  */

  @JsonProperty("p1ingr")
  public String getP1ingr() {
    return p1ingr;
  }

  public void setP1ingr(String p1ingr) {
    this.p1ingr = p1ingr;
  }

  public ProductRecord p2ingr(String p2ingr) {
    this.p2ingr = p2ingr;
    return this;
  }

  /**
   * Get p2ingr
   * @return p2ingr
  */

  @JsonProperty("p2ingr")
  public String getP2ingr() {
    return p2ingr;
  }

  public void setP2ingr(String p2ingr) {
    this.p2ingr = p2ingr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductRecord productRecord = (ProductRecord) o;
    return Objects.equals(this.percsil, productRecord.percsil) &&
        Objects.equals(this.product1, productRecord.product1) &&
        Objects.equals(this.product2, productRecord.product2) &&
        Objects.equals(this.ingrCommon, productRecord.ingrCommon) &&
        Objects.equals(this.p1ingr, productRecord.p1ingr) &&
        Objects.equals(this.p2ingr, productRecord.p2ingr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(percsil, product1, product2, ingrCommon, p1ingr, p2ingr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductRecord {\n");
    sb.append("    percsil: ").append(toIndentedString(percsil)).append("\n");
    sb.append("    product1: ").append(toIndentedString(product1)).append("\n");
    sb.append("    product2: ").append(toIndentedString(product2)).append("\n");
    sb.append("    ingrCommon: ").append(toIndentedString(ingrCommon)).append("\n");
    sb.append("    p1ingr: ").append(toIndentedString(p1ingr)).append("\n");
    sb.append("    p2ingr: ").append(toIndentedString(p2ingr)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

