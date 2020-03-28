package br.com.fiap.billing.api.person

import br.com.fiap.billing.api.core.exception.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.persistence.EntityNotFoundException

@Service
class PersonServiceImpl(private val repository: PersonRepository) : PersonService {

    override fun create(person: Person): Person {
        return repository.save(person)
    }

    override fun update(id: Long, person: Person): Person {
        val entity = getById(id)
        entity.doc = person.doc
        entity.city = person.city
        entity.state = person.state
        entity.jobType = person.jobType
        entity.name = person.name
        entity.active = true

        return repository.save(entity)
    }

    override fun delete(id: Long): Boolean {
        val entity = getById(id)
        entity.active = false
        repository.save(entity)
        return true
    }

    override fun getById(id: Long): Person {
        try {
            return repository.getOne(id)
        } catch (e: EntityNotFoundException) {
            throw ResourceNotFoundException("Person with id $id not found")
        }
    }

    override fun findById(id: Long): Person? {
        return repository.findById(id)
                .orElse(null)
    }

    override fun findAll(): List<Person> {
        return repository.findAll()
    }

    override fun findAllSummary(): List<PersonSummary> {
        return repository.findAllSummary().stream()
                .map {  this.convert(it) }
                .collect(Collectors.toList())
    }

    internal fun convert(findPersonSummary: PersonRepository.FindPersonSummary): PersonSummary {
        return PersonSummary(
                state = findPersonSummary.state,
                totalDoctors = findPersonSummary.totalDoctors,
                totalNurses = findPersonSummary.totalNurses,
                totalNursingTechnicals = findPersonSummary.totalNursingTechnicals,
                totalMaintainers = findPersonSummary.totalMaintainers,
                totalHelpers = findPersonSummary.totalHelpers)
    }
}