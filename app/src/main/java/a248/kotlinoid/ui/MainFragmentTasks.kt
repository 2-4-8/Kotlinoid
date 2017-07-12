package a248.kotlinoid.ui

import a248.kotlinoid.data.ObjectEnity
import android.os.AsyncTask

fun unitResultTask(preExecutor: () -> Unit,
               inBackground: () -> Unit,
               postExecutor: () -> Unit) {
    val task = object: AsyncTask<Void, Void, Unit>() {
        override fun onPreExecute() {
            super.onPreExecute()
            preExecutor()
        }
        override fun doInBackground(vararg params: Void?) {
            inBackground()
        }
        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            postExecutor()
        }
    }
    task.execute()
}

fun objectsResultTask(preExecutor: () -> Unit,
                      inBackground: () -> List<ObjectEnity>,
                      postExecutor: (List<ObjectEnity>?) -> Unit) {
    val task = object: AsyncTask<Void, Void, List<ObjectEnity>>() {
        override fun onPreExecute() {
            super.onPreExecute()
            preExecutor()
        }
        override fun doInBackground(vararg params: Void?): List<ObjectEnity> {
            return inBackground()
        }
        override fun onPostExecute(result: List<ObjectEnity>?) {
            super.onPostExecute(result)
            postExecutor(result)
        }
    }
    task.execute()
}