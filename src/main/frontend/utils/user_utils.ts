import { UserInfoService } from 'Frontend/generated/endpoints';

export async function getCurrentUser() {
  try {
    const userInfo = await UserInfoService.getUserInfo();

    if (!userInfo?.authorities || userInfo.authorities.includes('ROLE_ANONYMOUS')) {
      return null;
    }

    return userInfo;
  } catch {
    return null;
  }
}
