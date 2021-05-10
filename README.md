# Type-Writer
Android Text View Type Writer.(Based on Antarix's project 'https://gist.github.com/Antarix/6388606')

## How to use?
Copy or download the file to the corresponding place(Don't ask me where, 'Google' it).

## Some Attrs?
**delay:** *Customize the interval between every two words displayed by Type-Writer*

**autoStart:** *Seeing the name of a thing one thinks of its function...*

## Want all the Type-Writer of the whole activity to display in order?
You can use the following code in your 'Activity'(kotlin):

```kotlin
private var index: Int = 1

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.act_ld) // You can replace it with your own layout

    val mHandler = Handler()
    val runTypeWriter: Runnable = object : Runnable {
        override fun run() {
            val tw: TypeWriter = when (index) {
                1 -> tw_title
                2 -> tw_subtitle
                else -> return
            }
            tw.startAnimation()
            index++
            mHandler.postDelayed(this, tw.needTime())
        }
    }

    mHandler.post(runTypeWriter)
}
```

And then, here is part of the code of 'act_ld.xml':

```xml
<com.xxx.xxx.TypeWriter
    android:id="@+id/tw_title"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="@string/app_title"
    android:textColor="@color/white"
    android:textSize="36sp"
    android:textStyle="bold" />

<com.xxx.xxx.TypeWriter
    android:id="@+id/tw_subtitle"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_marginTop="32dp"
    android:text="@string/app_subtitle"
    android:textColor="@color/white" />
```
