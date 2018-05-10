package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.BuyDAO;
import dao.BuyDetailDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// URLからGETパラメータとしてIDを受け取る
		String id_str = request.getParameter("buy_id");
		int id = Integer.parseInt(id_str);

		//idに紐づく購入詳細情報を受け取る
		BuyDAO buyDao = new BuyDAO();
		BuyDataBeans bdb = null;
		try {
			bdb = buyDao.getBuyDataBeansByBuyId(id);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//idに紐づく購入商品情報を受け取る
		BuyDetailDAO buyDetailDao = new BuyDetailDAO();
		ArrayList<ItemDataBeans> idbList = null;
		try {
			idbList = buyDetailDao.getItemDataBeansListByBuyId(id);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//リクエストスコープにセット
		request.setAttribute("bdb", bdb);
		request.setAttribute("idbList", idbList);


		request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

	}
}
