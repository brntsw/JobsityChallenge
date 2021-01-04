package bruno.com.jobsitychallenge.data.mapper

interface Mapper<I, V> {

    fun mapFromRepoToView(entity: I): V

}