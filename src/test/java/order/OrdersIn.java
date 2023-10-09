package order;

import java.util.List;

public class OrdersIn {
    private List<OrderIn> orders;
    private OrderInPageInfo pageInfo;
    private List<OrderInStation> availableStations;

    public List<OrderIn> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderIn> orders) {
        this.orders = orders;
    }

    public OrderInPageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(OrderInPageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<OrderInStation> getAvailableStations() {
        return availableStations;
    }

    public void setAvailableStations(List<OrderInStation> availableStations) {
        this.availableStations = availableStations;
    }
}
