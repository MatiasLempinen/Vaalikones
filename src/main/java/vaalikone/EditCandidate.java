package vaalikone;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persist.Ehdokkaat;

/**
 * Servlet implementation class EditCandidate
 */
@WebServlet("/EditCandidate")
public class EditCandidate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCandidate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikones");
			EntityManager em = emf.createEntityManager();
			
			int candidateid = Integer.parseInt(request.getParameter("candidateid"));
			String candidatelname = request.getParameter("candidatelname");
			String candidatefname = request.getParameter("candidatefname");
			String party = request.getParameter("party");
			String homeCity = request.getParameter("homeCity");
			int age = Integer.parseInt(request.getParameter("age"));
			String whyToParliament = request.getParameter("whyToParliament");
			String whatAreTheThingsYouWantToAffect = request.getParameter("whatAreTheThingsYouWantToAffect");
			String profession = request.getParameter("profession");
			
			em.getTransaction().begin();
			
			Ehdokkaat ehdokas = new Ehdokkaat();
			
			ehdokas.setEhdokasId(candidateid);
			ehdokas.setSukunimi(candidatelname);
			ehdokas.setEtunimi(candidatefname);
			ehdokas.setPuolue(party);
			ehdokas.setKotipaikkakunta(homeCity);
			ehdokas.setIka(age);
			ehdokas.setMiksiEduskuntaan(whyToParliament);
			ehdokas.setMitaAsioitaHaluatEdistaa(whatAreTheThingsYouWantToAffect);
			ehdokas.setAmmatti(profession);
			
			em.persist(ehdokas);
			
			em.getTransaction().commit();
			
			em.close();
			
			response.getWriter().println(ehdokas + "<br>");
			
		} catch (Exception e) {
			response.getWriter().println("Couldn't edit candidate information.");
			response.getWriter().println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
