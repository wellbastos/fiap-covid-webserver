package br.com.fiap.billing.api.person

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepository : JpaRepository <Person, Long> {

    @Query(value = "" +
            "select " +
            "   state, " +
            "   count(*) filter (where job_type = 0) as totalNurses, " +
            "   count(*) filter (where job_type = 1) as totalDoctors, " +
            "   count(*) filter (where job_type = 2) as totalMaintainers, " +
            "   count(*) filter (where job_type = 3) as totalNursingTechnicals, " +
            "   count(*) as totalHelpers " +
            "from person p " +
            "group by state ",
            nativeQuery = true)
    abstract fun findAllSummary(): List<FindPersonSummary>


    interface FindPersonSummary {
        val state: String
        val totalNurses: Int
        val totalDoctors: Int
        val totalNursingTechnicals: Int
        val totalMaintainers: Int
        val totalHelpers: Int
    }

}