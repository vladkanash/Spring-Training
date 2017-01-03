package com.expertsoft.core.dao.impl;

class JdbcConstants {

    private JdbcConstants() {}

    final static String ORDER_TABLE = "PHONIFY_ORDER";
    final static String ORDER_ITEM_TABLE = "ORDER_ITEM";
    final static String PHONE_TABLE = "PHONE";
    final static String USERS_TABLE = "USERS";
    final static String AUTHORITIES_TABLE = "AUTHORITIES";

    final static String ORDER_ITEM_KEY_COLUMN = "KEY";
    final static String ORDER_ITEM_QUANTITY_COLUMN = "QUANTITY";
    final static String ORDER_ITEM_PHONE_COLUMN = "PHONE_FK";
    final static String ORDER_ITEM_ORDER_COLUMN = "ORDER_FK";

    final static String ORDER_KEY_COLUMN = "KEY";
    final static String ORDER_USER_COLUMN_NAME = "USERNAME";
    final static String ORDER_FIRST_NAME_COLUMN = "FIRST_NAME";
    final static String ORDER_LAST_NAME_COLUMN = "LAST_NAME";
    final static String ORDER_TOTAL_PRICE_COLUMN = "TOTAL_PRICE";
    final static String ORDER_DELIVERY_ADDRESS_COLUMN = "DELIVERY_ADDRESS";
    final static String ORDER_CONTACT_PHONE_COLUMN = "CONTACT_PHONE";
    final static String ORDER_SHIPPING_PRICE_COLUMN = "SHIPPING_PRICE";
    final static String ORDER_DELIVERED_COLUMN_NAME = "DELIVERED";

    final static String PHONE_KEY_COLUMN = "KEY";
    final static String PHONE_MODEL_COLUMN = "MODEL";
    final static String PHONE_COLOR_COLUMN = "COLOR";
    final static String PHONE_PRICE_COLUMN = "PRICE";

    final static String USERS_USERNAME_COLUMN = "USERNAME";
    final static String USERS_PASSWORD_COLUMN = "PASSWORD";
    final static String USERS_ENABLED_COLUMN = "ENABLED";

    final static String AUTHORITIES_USERNAME_COLUMN = "USERNAME";
    final static String AUTHORITIES_AUTHORITY_COLUMN = "AUTHORITY";
}
