import { UserInfoService } from 'Frontend/generated/endpoints';
import UserInfoDto from 'Frontend/generated/com/bobbysoft/application/usermanagement/dto/UserInfoDto';

export async function getCurrentUser() {
  try {
    const userInfo = await UserInfoService.getUserInfo();

    return userInfo;
  } catch {
    return {};
  }
}

export function isLoggedInUser(userInfo: UserInfoDto) {
  if (!userInfo.authorities || !userInfo.authorities.includes('ROLE_USER')) {
    return false;
  }

  return true;
}
