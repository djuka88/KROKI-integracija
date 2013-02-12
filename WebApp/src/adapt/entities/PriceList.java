package adapt.entities;

import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.CascadeType;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;

   /** 
   Class generated using Kroki EJBGenerator 
   @Author mrd 
   Creation date: 11.02.2013  16:15:23h
   **/

@Entity
@Table(name = "PRICELIST")
public class PriceList implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private java.lang.Long id;

	@Column(name = "PL_ACTIVE_FROM_DATE", unique = false, nullable = false)
	private java.util.Date activeFrom;
	
	@Column(name = "PL_NUMBER", unique = false, nullable = false)
	private java.lang.String name;
	
	@OneToMany(cascade = { ALL }, fetch = FetchType.LAZY, mappedBy = "list")
	private Set<PriceListItem> priceListItemSet = new HashSet<PriceListItem>();
	
	public PriceList(){
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public java.util.Date getActiveFrom() {
		return this.activeFrom;
	}
	
	public void setActiveFrom(java.util.Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	
	public java.lang.String getName() {
		return this.name;
	}
	
	public void setName(java.lang.String name) {
		this.name = name;
	}
	
	public Set<PriceListItem> getPriceListItemSet() {
		return this.priceListItemSet;
	}

	public void setPriceListItemSet(Set<PriceListItem> priceListItemSet) {
		this.priceListItemSet = priceListItemSet;
	}
	
}