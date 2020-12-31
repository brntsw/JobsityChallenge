package bruno.com.jobsitychallenge.data

interface IRequest<T> {

    fun onSuccess(result: T)
    fun onError(message: String)

}