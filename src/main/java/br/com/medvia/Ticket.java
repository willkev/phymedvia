package br.com.medvia;

import java.util.Date;

/**
 *
 * @author Willian
 */

/*
{
               id: '',
               state: 'f',
               description: "Não liga",
               institution: "Hospital de Clínicas (POA)",
               equipment: "RM SPECTRIS SOLARIS EX THUNDER",
               situation: 0,
               priority: "n",
               dateOcurrence: "22/05/2016 23:08",
               prediction: "02/08/2016 10:45",
               openedBy: "Dr. Oliver Tsubasa",
               responsable: "Dr. Alberto A."
           };
 */
public class Ticket {

    private int id;
    private int state;
    private String description;
    private transient Date dateOcurrenceRaw;
    private String dateOcurrence;
    private transient Date predictionRaw;
    private String prediction;
    private String openedBy;
    private String responsable;
    private String institution;
    private String equipment;
    private int situation;
    private String priority;

    public void setState(boolean state) {
        this.state = state ? 1 : 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOcurrenceRaw(Date dateOcurrenceRaw) {
        this.dateOcurrenceRaw = dateOcurrenceRaw;
    }

    public Date getDateOcurrenceRaw() {
        return dateOcurrenceRaw;
    }

    public void setDateOcurrence(String dateOcurrence) {
        this.dateOcurrence = dateOcurrence;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setSituation(int situation) {
        this.situation = situation;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setPredictionRaw(Date predictionRaw) {
        this.predictionRaw = predictionRaw;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public void setOpenedBy(String openedBy) {
        this.openedBy = openedBy;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

}
