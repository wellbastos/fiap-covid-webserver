package br.com.fiap.billing.api.person.web

import br.com.fiap.billing.api.core.exception.ResourceNotFoundException
import br.com.fiap.billing.api.person.Person
import br.com.fiap.billing.api.person.PersonService
import br.com.fiap.billing.api.person.PersonSummary
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@Api("CRUD de pessoas")
@RestController
@RequestMapping("/help-covid/persons")
class PersonRestService(private val service: PersonService) {

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca a pessoa com o Id informado.")
    fun get(@PathVariable id: Long): Person {
        val entity = service.getById(id)

        if (!entity.active) {
            throw ResourceNotFoundException("Resource with id $id was not found.")
        }

        return entity;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todas as pessoas.")
    fun findAll(): List<Person> {
        return service.findAll()
                .filter { it -> it.active }
    }


    @GetMapping("/summary")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca um resumo de pessoas cadastradas.")
    fun findAllSummary(): List<PersonSummary> {
        return service.findAllSummary()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva um novo registro de pessoa.")
    fun post(@RequestBody person: Person): Person {
        return service.create(person)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Salva o registro de pessoa.")
    fun put(@PathVariable id: Long,
            @RequestBody person: Person): Person {
        return service.update(id, person)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation("Deleta o registro da pessoa por Id")
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

}