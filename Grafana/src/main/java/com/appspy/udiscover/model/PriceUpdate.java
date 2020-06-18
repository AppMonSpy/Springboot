package com.appspy.udiscover.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"PriceUpdate"
})
public class PriceUpdate {

	@JsonProperty("PriceUpdate")
	private List<List<List<List>>> priceUpdate = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("PriceUpdate")
	public List<List<List<List>>> getPriceUpdate() {
		return priceUpdate;
	}

	@JsonProperty("PriceUpdate")
	public void setPriceUpdate(List<List<List<List>>> priceUpdate) {
		this.priceUpdate = priceUpdate;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
