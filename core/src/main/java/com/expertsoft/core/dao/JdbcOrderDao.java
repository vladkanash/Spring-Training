package com.expertsoft.core.dao;

import com.expertsoft.core.model.Order;
import com.expertsoft.core.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderDao implements OrderDao {

    private final static String SELECT_ALL_QUERY = "SELECT  * FROM PHONIFY_ORDER";
    private static final String SELECT_QUERY = "SELECT * FROM PUBLIC.PHONIFY_ORDER WHERE KEY=?";

    private final JdbcOperations jdbcOperations;
    private final RowMapper<Order> orderRowMapper;
    private final SimpleJdbcInsert jdbcInsert;
    private final OrderItemDao orderItemDao;

    @Autowired
    public JdbcOrderDao(JdbcOperations jdbcOperations,
                        RowMapper<Order> orderRowMapper,
                        DataSource dataSource,
                        OrderItemDao orderItemDao) {
        this.jdbcOperations = jdbcOperations;
        this.orderRowMapper = orderRowMapper;
        this.orderItemDao = orderItemDao;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName(JdbcConstants.ORDER_TABLE)
                .usingGeneratedKeyColumns(JdbcConstants.ORDER_KEY_COLUMN);
    }

    public Order getOrder(long key) {
         return jdbcOperations.queryForObject(SELECT_QUERY, orderRowMapper, key);
    }

    public void saveOrder(Order order) {
        Map<String, Object> parameters = new HashMap<>(5);
        parameters.put(JdbcConstants.ORDER_FIRST_NAME_COLUMN, order.getFirstName());
        parameters.put(JdbcConstants.ORDER_LAST_NAME_COLUMN, order.getLastName());
        parameters.put(JdbcConstants.ORDER_TOTAL_PRICE_COLUMN, order.getTotalPrice());
        parameters.put(JdbcConstants.ORDER_CONTACT_PHONE_COLUMN, order.getContactPhone());
        parameters.put(JdbcConstants.ORDER_DELIVERY_ADDRESS_COLUMN, order.getDeliveryAddress());

        Number newId = jdbcInsert.executeAndReturnKey(parameters);
        order.setKey(newId.longValue());

        if (null != order.getOrderItems()) {
            for (OrderItem item : order.getOrderItems()) {
                item.setOrder(order);
                orderItemDao.saveOrderItem(item);
            }
        }
    }

    public List<Order> findAll() {
        return jdbcOperations.query(SELECT_ALL_QUERY, orderRowMapper);
    }

    public void close() {

    }
}
