package com.stephen.careTrack.helper;

import com.stephen.careTrack.DTO.VisitDTO;
import com.stephen.careTrack.model.Visit;

public class VisitMapper {
    public static VisitDTO entityToDTO(Visit visit)
    {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setVisit_date(visit.getVisit_date());
        visitDTO.setBmi(visit.getBmi());
        visitDTO.setHeight(visit.getHeight());
        visitDTO.setWeight(visit.getWeight());
        visitDTO.setGeneral_health(visit.getGeneral_health());
        visitDTO.setTaking_drugs(visit.isTaking_drugs());
        visitDTO.setOn_diet(visit.isOn_diet());
        visitDTO.setComments(visit.getComments());
        visitDTO.setStatus(visit.getStatus());
        return visitDTO;
    }

    public static Visit dtoToEntity(VisitDTO visitDTO)
    {
        Visit visit = new Visit();
        visit.setVisit_date(visitDTO.getVisit_date());
        visit.setBmi(visitDTO.getBmi());
        visit.setHeight(visitDTO.getHeight());
        visit.setWeight(visitDTO.getWeight());
        visit.setGeneral_health(visitDTO.getGeneral_health());
        visit.setTaking_drugs(visitDTO.isTaking_drugs());
        visit.setOn_diet(visitDTO.isOn_diet());
        visit.setComments(visitDTO.getComments());
        visit.setStatus(visitDTO.getStatus());
        return visit;
    }
}
