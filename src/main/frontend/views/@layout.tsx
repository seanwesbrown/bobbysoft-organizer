/* eslint-disable @typescript-eslint/no-explicit-any */
import { Outlet, useNavigate } from 'react-router';
import {
  AppLayout,
  Avatar,
  Button,
  HorizontalLayout,
  Icon,
  MenuBar,
  MenuBarItemSelectedEvent,
  Popover,
  ProgressBar,
  VerticalLayout,
} from '@vaadin/react-components';
import { Suspense } from 'react';
import { useAuth } from 'Frontend/security/auth';
import { getCurrentUser, isLoggedInUser } from 'Frontend/utils/user_utils';
import UserInfoDto from 'Frontend/generated/com/bobbysoft/application/usermanagement/dto/UserInfoDto';

const currentUser = await getCurrentUser();

function Header() {
  return (
    <div className="bobby-header flex p-m gap-m items-center" slot="navbar">
      <Icon icon="vaadin:cubes" className="text-primary icon-l" />
      <span className="font-semibold text-l">Bobbysoft</span>
    </div>
  );
}

function NavBar() {
  return (
    <HorizontalLayout slot="navbar touch-optimized" className="bobby-navbar w-full justify-evenly self-stretch">
      {isLoggedInUser(currentUser) && NavbarLinks()}
    </HorizontalLayout>
  );
}

function NavbarLinks() {
  return [
    <a href="/organizer" aria-label="My Organizer" className="flex items-center px-m text-secondary font-medium">
      <Icon icon="vaadin:dashboard" aria-label="My Organizer" />
      <p>My Organizer</p>
    </a>,
    <a href="/create" aria-label="Create" className="flex items-center px-m text-secondary font-medium">
      <Icon icon="vaadin:pencil" aria-label="Create" />
      <p>Create</p>
    </a>,
    <a href="/share" aria-label="Share" className="flex items-center px-m text-secondary font-medium">
      <Icon icon="vaadin:share-square" aria-label="Share" />
      <p>Share</p>
    </a>,
  ];
}

function UserMenu() {
  const items = UserMenuItems(currentUser);

  const onItemSelected = (event: MenuBarItemSelectedEvent) => {
    const action = (event.detail.value as any).action;
    if (action) {
      action();
    }
  };

  return (
    <MenuBar theme="tertiary-inline" items={items} onItemSelected={onItemSelected} className="m-m" slot="navbar" />
  );
}

function UserMenuItems(user: UserInfoDto) {
  return [
    {
      component: UserMenuPopover(user),
    },
  ];
}

function UserMenuPopover(user: UserInfoDto) {
  const popoverOptions = PopoverOptions(user);
  const userAvatar = UserAvatar(user);

  return (
    <>
      {userAvatar}
      <Popover key="user-popover" className="bobby-user-popover" for="user-menu" modal position="bottom">
        <div className="bobby-user-popover">
          {isLoggedInUser(user) && (
            <>
              <HorizontalLayout>
                <p>
                  Welcome <b>{user.name}</b>!
                </p>
              </HorizontalLayout>
              <hr />
            </>
          )}

          <VerticalLayout style={{ alignItems: 'center' }}>{popoverOptions}</VerticalLayout>
        </div>
      </Popover>
    </>
  );
}

function UserAvatar(user: UserInfoDto) {
  if (isLoggedInUser(user)) {
    return <Avatar key="avatar" id="user-menu" theme="small" name={user.name} colorIndex={5} className="mr-s" />;
  }

  return <Avatar key="avatar" id="user-menu" theme="small" className="mr-s" />;
}

function PopoverOptions(user: UserInfoDto) {
  const { logout } = useAuth();
  const navigate = useNavigate();

  if (!isLoggedInUser(user)) {
    return [
      <Button key="login" onClick={() => navigate('/login')}>
        Login
      </Button>,
      <Button key="signup" onClick={() => navigate('/signup')}>
        Sign Up
      </Button>,
    ];
  }

  return [
    <Button key="profile" onClick={() => console.log('View Profile')}>
      View Profile
    </Button>,
    <Button key="settings" onClick={() => console.log('Manage Settings')}>
      Manage Settings
    </Button>,
    <Button
      key="logout"
      onClick={async () => {
        await logout();
      }}>
      Logout
    </Button>,
  ];
}

export default function MainLayout() {
  return (
    <AppLayout primarySection="navbar" className="bobby-app-layout">
      <Header />
      <NavBar />
      <UserMenu />
      <Suspense fallback={<ProgressBar indeterminate={true} className="m-0" />}>
        <Outlet />
      </Suspense>
    </AppLayout>
  );
}
