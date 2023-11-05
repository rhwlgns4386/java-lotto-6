package lotto.controller;

import lotto.domain.LottoService;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Money.NumericString;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money money = Money.of(new NumericString(InputView.collectionOfMoney()));

        Lottos lottos = Lottos.from(money.calcBillCount());
        LottoService service = new LottoService(lottos);

        OutputView.printPurchaseHistory(lottos.getList());
    }

}
