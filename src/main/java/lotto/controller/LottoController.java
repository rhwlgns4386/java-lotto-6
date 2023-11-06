package lotto.controller;

import java.util.Arrays;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoService;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.NumberCandidateString;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        LottoService lottoService = initLottoService();
        String winningNumber = InputView.receiveWinningNumber();
        String[] split = winningNumber.split(",");
        List<NumberCandidateString> numberCandidateStrings = Arrays.stream(split)
                .map(NumberCandidateString::new).toList();
        List<Integer> numbers = numberCandidateStrings.stream()
                .map(NumberCandidateString::getNumber).toList();
        Lotto lotto = new Lotto(numbers);
    }

    private LottoService initLottoService() {
        NumberCandidateString numberCandidateString = new NumberCandidateString(InputView.collectionOfMoney());
        Money money = Money.of(numberCandidateString.getNumber());

        Lottos lottos = Lottos.from(money.calcBillCount());
        LottoService service = new LottoService(lottos);

        OutputView.printPurchaseHistory(lottos.getList());
        return service;
    }

}
