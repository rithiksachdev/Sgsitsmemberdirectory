package picker;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;

public class GalleryPickerUtil {
    public static final int GALLERY_PHOTO =1;
    public static final int CAPTURE_PHOTO=2;
    public static void launchGallery(Activity activity)
    {
        Intent intent =new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent,"Select Picture"),GALLERY_PHOTO);
    }
    public static void launchGallery(Fragment fragment)
    {
        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        fragment.startActivityForResult(Intent.createChooser(intent,"Select Photo"),GALLERY_PHOTO);
    }

    public static File createTeampFile()
    {
        try {
            File file = File.createTempFile(System.currentTimeMillis() + "temp.jpg",
                    null,
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES));
            return file;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
