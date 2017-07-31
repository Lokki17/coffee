import com.coffee.shop.dao.entity.CoffeeCup
import com.coffee.shop.dao.entity.Configuration
import com.coffee.shop.dao.entity.Order
import com.coffee.shop.service.PriceGenerator

class PriceGeneratorImpl implements PriceGenerator {

    @Override
    void setPrice(Order order, Configuration configuration) {
        BigDecimal price = getSumm(order, configuration);
        if (price.compareTo(configuration.getDiscountSumm()) < 0) {
            price = price.add(configuration.getDeliveryPrice())
        }

        order.setPrice(price);
    }

    private BigDecimal getSumm(Order order, Configuration configuration) {
        BigDecimal summ = BigDecimal.ZERO;

        for (CoffeeCup cup : order.getCups()) {
            Integer countFree = cup.getCount() / configuration.getCupCount();
            BigDecimal currentSumm = cup.getCoffeeKind().getPrice().multiply(new BigDecimal(cup.getCount() - countFree));
            summ = summ.add(currentSumm);
        }
        return summ;
    }
}
