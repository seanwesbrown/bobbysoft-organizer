import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { LoginForm } from '@vaadin/react-components';
import { useNavigate, useSearchParams } from 'react-router';
import { useEffect } from 'react';

import { getCurrentUser, isLoggedInUser } from 'Frontend/utils/user_utils';

export const config: ViewConfig = {
  title: 'Login',
};

const currentUser = await getCurrentUser();

export default function LoginView() {
  const navigate = useNavigate();
  const [searchParams] = useSearchParams();
  const hasError = searchParams.has('error');

  useEffect(() => {
    if (isLoggedInUser(currentUser)) {
      navigate('/');
    }
  }, []);

  return (
    <main className="bobby-login-view w-full h-full flex flex-col box-border gap-s p-m">
      <LoginForm error={hasError} action="login" />
    </main>
  );
}
