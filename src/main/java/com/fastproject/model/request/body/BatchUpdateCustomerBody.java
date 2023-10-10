package com.fastproject.model.request.body;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

/**
 * @author fastProject
 * @date 2023/10/10 0:14
 */
@Data
public class BatchUpdateCustomerBody {

  private String ids;
  private String salesManager;
  private String businessSales;
  private String description;

  public List<Long> getIds() {
    return Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toList());
  }
}
