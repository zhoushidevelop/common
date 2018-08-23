package com.zhou.commonlibrary.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.zhou.commonlibrary.R;

import java.io.File;

/**
 * Description : 图片加载工具类 使用glide框架封装
 */
public class ImageLoaderUtils {

    private static RequestOptions options = new RequestOptions();

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .apply(options
                        .placeholder(placeholder)
                        .error(error)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .apply(options
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_empty_picture)
                ).transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, File url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .apply(options
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_empty_picture)
                )
                .transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
    }

    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .thumbnail(0.5f)
                .apply(options
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_empty_picture)
                )
                .into(imageView);
    }

    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).apply(options
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture))
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .apply(options
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_loading)
                        .error(R.drawable.ic_empty_picture))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .apply(options
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop()
                        .transform(new GlideRoundTransformUtil(context))
                        .error(R.drawable.toux2))
                .into(imageView);
    }

    public static void displayRound(Context context, ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(resId)
                .apply(options
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .error(R.drawable.toux2)
                        .centerCrop().transform(new GlideRoundTransformUtil(context))).into(imageView);
    }

}
