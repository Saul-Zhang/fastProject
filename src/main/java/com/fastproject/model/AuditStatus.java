package com.fastproject.model;

/**
 * @author fastProject
 * @date 2023/6/24 22:38
 */
public enum AuditStatus {
  // 等待审批，二级审批，通过，拒绝
  WAITING, WAITING_SECOND, APPROVED, REJECTION
}
