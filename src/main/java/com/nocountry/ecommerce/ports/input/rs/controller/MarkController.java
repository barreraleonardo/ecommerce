package com.nocountry.ecommerce.ports.input.rs.controller;

import com.nocountry.ecommerce.domain.usecase.MarkService;
import com.nocountry.ecommerce.ports.input.rs.mapper.MarkMapper;
import com.nocountry.ecommerce.ports.input.rs.request.MarkRequest;
import com.nocountry.ecommerce.ports.input.rs.response.MarkDetails;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

import static com.nocountry.ecommerce.ports.input.rs.api.ApiConstants.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(MARK_URI)
public class MarkController {

    private final MarkService markService;
    private final MarkMapper mapper;

    //====================Display all====================//

    @GetMapping
    @ApiOperation("display a list of marks")
    public ResponseEntity<List<MarkDetails>> getAllProducts() {
        return ResponseEntity.ok(mapper.MarkListToMarkDetailList(markService.findAllActive()));
    }

    //====================Get one by id====================//

    @ApiOperation("get a mark by id")
    @GetMapping(path = "/{id}")
    public ResponseEntity<MarkDetails> getById(@Valid @NotNull @PathVariable("id") Long id) {
        return ResponseEntity.ok(mapper.MarkToMarkDetails(markService.getByIdIfExists(id)));
    }


    //====================Create====================//

    @PreAuthorize(ADMIN)
    @ApiOperation("create a mark")
    @PostMapping(path = "/create")
    public ResponseEntity<Void> createMark(@RequestBody MarkRequest markCreateRequest) {
        long id = markService.save(mapper.MarkRequestToMark(markCreateRequest));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    //====================Update====================//

    @ApiOperation("update data mark")
    @PreAuthorize(ADMIN)
    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMark(@Valid @NotNull @PathVariable("id") Long id,
                           @RequestBody MarkRequest request) {
        markService.update(id, mapper.MarkRequestToMark(request));
    }

    @ApiOperation("update is available")
    @PreAuthorize(ADMIN)
    @PatchMapping(path = "/available/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAvailable(@Valid @NotNull @PathVariable("id") Long id) {
        markService.updateAvailable(id);
    }


    //====================Deletes====================//

    @ApiOperation("delete a mark")
    @PreAuthorize(ADMIN)
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMark(@Valid @NotNull @PathVariable Long id) {
        markService.deleteById(id);
    }
}
