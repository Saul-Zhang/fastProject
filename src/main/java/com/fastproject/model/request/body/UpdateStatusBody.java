package com.fastproject.model.request.body;

import java.util.List;
import lombok.Data;

/**
 * @author fastproject
 * @date 2023/6/14 22:31
 */
@Data
public class UpdateStatusBody {

  private List<Long> ids;
  private Character status;
}
