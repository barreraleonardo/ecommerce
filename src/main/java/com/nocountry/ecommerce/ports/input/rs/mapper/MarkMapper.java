package com.nocountry.ecommerce.ports.input.rs.mapper;

import com.nocountry.ecommerce.domain.model.Mark;
import com.nocountry.ecommerce.ports.input.rs.request.MarkRequest;
import com.nocountry.ecommerce.ports.input.rs.response.MarkDetails;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MarkMapper {

    Mark MarkRequestToMark(MarkRequest request);

    MarkDetails MarkToMarkDetails(Mark mark);

    List<MarkDetails> MarkListToMarkDetailList(List<Mark> list);
}
