import { EsportsRosterPage } from './app.po';

describe('esports-roster App', () => {
  let page: EsportsRosterPage;

  beforeEach(() => {
    page = new EsportsRosterPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
