package northgate.northgate.exercise;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Person {
	@JsonProperty("person_id")
	@EqualsAndHashCode.Exclude 
	String personId;
	@JsonProperty("first_name")
	String firstName;
	@JsonProperty("middle_name")
	String middleName;
	@JsonProperty("last_name")
	String lastName;
	@JsonProperty("date_of_birth")
	 @JsonFormat
     ( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date dateOfBirth;

	public int getAge() {
		LocalDate today = LocalDate.now();
		Period p = Period.between(this.dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today);
		return p.getYears();
	}
	
	/*
    "created_datetime": "2020-04-14 08:17:30.660950",
    "created_by_username": "Unable to get username",
    "updated_datetime": "2020-04-14 08:17:30.660950",
    "updated_by_username": "Unable to get username",
    "date_of_birth": "2000-04-14",
    "deleted": false,
    "gender": 2,
    "ethnicity": 3,
    "nationality": 4,
    "preferred_language": 5,
    "religion": 6,
    "other": null,
    "status_id": 29001,
    "person_reference_number": "LN43385",
    "mobile_phone": null,
    "other_phone": null,
    "email_address": null,
    "staff_allocation": null,
    "team_allocation": null,
    "interpreter_required": null,
    "person_custody_details_id": null,
    "team_id": null,
    "org_id": null,
    "area_id": null*/

}
