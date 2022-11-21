package ru.Inside_test_project.controller;

import ru.Inside_test_project.dto.MessageDTO;

public class TestDataUtil {

  public static final String AUTHREQUEST_JSON_STRING = "{\"name\":\"admin\",\"password\":\"password\"}";
  public static final String ANY_MESSAGE_JSON_STRING = "{\"name\":\"admin\",\"message\":\"any message for mock\"}";
  public final MessageDTO MESSAGE_DTO_ANY_MESSAGE = new MessageDTO("admin","any message");
  public static final String HISTORY_10_MESSAGE_JSON_STRING = "{\"name\":\"admin\",\"message\":\"history 10\"}";
  public static final String HISTORY_10 = "history 10";
  public static final String ANY_MESSAGE = "any message for mock";
  public static final String ADMIN_NAME = "admin";
  public static final String USER_NAME = "user";


}
