package fr.diginamic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import fr.diginamic.entite.Organisation;

public class OrganisationDao implements Dao<Organisation> {
	

	private EntityManager em;
	private TypedQuery<Organisation> querySelect;
	
	

	public OrganisationDao(EntityManager em) {
		this.em = em;
		this.querySelect = this.em
				.createQuery("select o from Organisation o " + "where o.cio= :p1", Organisation.class);
	}

	@Override
	public List<Organisation> selectAll(EntityManager em) {
		// TODO Auto-generated method stub
		TypedQuery<Organisation> query = em.createQuery("select o from Organisation o", Organisation.class);

		List<Organisation> organisation = query.getResultList();
		return organisation;
	}

	@Override
	public Organisation insertIfNotExistCustom(EntityManager em, Organisation t) {
		// TODO Auto-generated method stub
		em.persist(t);
		return null;
	}

	public Organisation insertIfNotExistCustom(String code) {
		// TODO Auto-generated method stub
		Organisation exOrganisation = selectCustom(code);
		if (exOrganisation == null) {
			try {
				Organisation nvOrganisation = new Organisation(code);
				em.persist(nvOrganisation);
				return nvOrganisation;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
		}

		return exOrganisation;

	}

	@Override
	public int updateCustom(EntityManager em, Organisation t1, Organisation t2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateCustom(Organisation organisation, String paysFr, String paysEng, String iso,
			Boolean obsolete) {
		// TODO Auto-generated method stub
		organisation.setPaysFr(paysFr);
		organisation.setPaysEng(paysEng);
		organisation.setIso(iso);
		organisation.setObsolete(obsolete);
		return 1;
	}

	@Override
	public boolean deleteCustom(EntityManager em, Organisation t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Organisation selectCustom(EntityManager em, Organisation t) {
		// TODO Auto-generated method stub

		return null;

	}

	public Organisation selectCustom(String code) {
		// TODO Auto-generated method stub
		querySelect
				.setParameter("p1", code);

		List<Organisation> organisation = querySelect.getResultList();

		if (organisation.size() == 0) {
			return null;
		}
		return organisation.get(0);

	}

}
