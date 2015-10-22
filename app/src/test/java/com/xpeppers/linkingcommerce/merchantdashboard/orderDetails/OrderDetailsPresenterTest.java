package com.xpeppers.linkingcommerce.merchantdashboard.orderDetails;

import com.xpeppers.linkingcommerce.merchantdashboard.orders.Order;

import org.junit.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderDetailsPresenterTest {

    public static final String BUYER_EMAIL = "dev@xpeppers.com";
    public static final String TITLE = "luganica";

    @Test
    public void shows_buyer_email() {
        OrderDetailsView view = mock(OrderDetailsView.class);
        OrderDetailsPresenter presenter = new OrderDetailsPresenter(view);
        Order order = new Order();
        order.setBuyerEmail(BUYER_EMAIL);

        presenter.show(order);

        verify(view).showBuyerEmail(eq(BUYER_EMAIL));
    }

    @Test
    public void shows_order_title() {
        OrderDetailsView view = mock(OrderDetailsView.class);
        OrderDetailsPresenter presenter = new OrderDetailsPresenter(view);
        Order order = new Order();
        order.setTitle(TITLE);

        presenter.show(order);

        verify(view).showTitle(eq(TITLE));
    }

    @Test
    public void shows_purchase_date() {
        OrderDetailsView view = mock(OrderDetailsView.class);
        OrderDetailsPresenter presenter = new OrderDetailsPresenter(view);
        Order order = new Order();
        order.setDate("2015-09-28T13:55:57Z");

        presenter.show(order);

        verify(view).showPurchaseDate(eq("28/09/2015 13:55"));
    }

    public class OrderDetailsPresenter {
        private OrderDetailsView view;

        public OrderDetailsPresenter(OrderDetailsView view) {
            this.view = view;
        }

        public void show(Order order) {
            view.showBuyerEmail(order.getBuyerEmail());
            view.showTitle(order.getTitle());
            view.showPurchaseDate(convertDate(order.getDate()));
        }

        public String convertDate(final String ISO8601String) {
            try {
                String[] splitISO8601String = ISO8601String.split("T");

                String[] splitDate = splitISO8601String[0].split("-");
                String convertedDate = splitDate[2] + "/" + splitDate[1] + "/" + splitDate[0];

                String[] splitTime = splitISO8601String[1].split(":");
                String convertTime = splitTime[0] + ":" + splitTime[1];

                return convertedDate + " " + convertTime;
            } catch (Exception ex) {
                return "";
            }
        }
    }

    public interface OrderDetailsView {
        void showBuyerEmail(String email);

        void showTitle(String title);

        void showPurchaseDate(String purchaseDate);
    }
}
