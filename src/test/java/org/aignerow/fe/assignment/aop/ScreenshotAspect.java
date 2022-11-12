package org.aignerow.fe.assignment.aop;

import org.aignerow.fe.assignment.annotations.TakeScreenshot;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import java.io.IOException;
import org.aignerow.fe.assignment.utils.ScreenshotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ScreenshotAspect {
  @Autowired
  private ScreenshotUtil screenshotUtil;

  @After("@annotation(takeScreenshot)")
  public void after(JoinPoint joinPoint, TakeScreenshot takeScreenshot) throws IOException {
    this.screenshotUtil.takeScreenShot(joinPoint.getSignature().getName());
  }
}
