package com.edgewalk.accidence.test;

import com.sun.nio.file.SensitivityWatchEventModifier;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * 1）对指定目录及其子目录下文件及文件夹监控有效，如果创建了更深的目录，则不再有效
 * 		监听:d:/test 只能监听到D:\test\aa 这一级下面的活动,D:\test\aa\bb里面的活动监听不到
 * 2）对指定的文件夹属性无法改动，比如：指定监听d:/test目录，如果修改或删除b目录名称是无法监听的
 * 3) 只能监控文件夹,不能监控文件
 */
@Slf4j
public class WatchServiceDemo {
	public static void main(String[] args) throws IOException {
		//获取文件监控的类WatchServcie
		WatchService watchService = FileSystems.getDefault().newWatchService();
		//设置监控指定的路径
		Path dir = Paths.get("d:/test");
		//参数1:WatchService,参数2:文件监控类型,参数3:文件监控灵敏度
		dir.register(watchService,
					new WatchEvent.Kind[]
						{StandardWatchEventKinds.ENTRY_MODIFY,
						StandardWatchEventKinds.ENTRY_CREATE,
						StandardWatchEventKinds.ENTRY_DELETE},
				SensitivityWatchEventModifier.HIGH);//可选参数
		new Thread(() -> {
			try {
				while (true) {
					//watchService.take():该方法是阻塞方法，如果没有文件修改，则一直阻塞。
					WatchKey watchKey = watchService.take();
					List<WatchEvent<?>> watchEvents = watchKey.pollEvents();
					for (WatchEvent<?> event : watchEvents) {
						/**
						 * TODO 根据事件类型采取不同的操作
						 * ENTRY_CREATE :创建事件需要注意:上传文件并没有完全成功就会立即被监控到
						 * ,如果我们要在文件创建完成后,做一些处理,那么可以通过比较5s前后的文件大小来判断
						 */

						System.out.printf("事件:%s,目录:%s\n",
								event.kind().name(),
								dir + File.separator + event.context());
					}
					//每次的到新的事件后，需要重置监听池
					watchKey.reset();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();

		//添加jvm关闭时候的钩子,kill -15可以kill -9 不行
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			try {
				//关闭wathcservice服务
				watchService.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}));

	}

}
