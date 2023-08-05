package com.example.ProductComparison.api.model;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;





/**
 * HistoryResponse
 */


public class HistoryResponse {

  private List<ProductRecord> productRecords;

  public HistoryResponse productRecords(List<ProductRecord> productRecords) {
    this.productRecords = productRecords;
    return this;
  }

  public HistoryResponse addProductRecordsItem(ProductRecord productRecordsItem) {
    if (this.productRecords == null) {
      this.productRecords = new ArrayList<>();
    }
    this.productRecords.add(productRecordsItem);
    return this;
  }

  /**
   * Get productRecords
   * @return productRecords
  */

  @JsonProperty("ProductRecords")
  public List<ProductRecord> getProductRecords() {
    return productRecords;
  }

  public void setProductRecords(List< ProductRecord> productRecords) {
    this.productRecords = productRecords;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HistoryResponse historyResponse = (HistoryResponse) o;
    return Objects.equals(this.productRecords, historyResponse.productRecords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productRecords);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HistoryResponse {\n");
    sb.append("    productRecords: ").append(toIndentedString(productRecords)).append("\n");
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

